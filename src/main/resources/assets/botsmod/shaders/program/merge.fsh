#version 150

uniform sampler2D DiffuseSampler;
uniform sampler2D TerrainDepthSampler;
uniform sampler2D HandDepthSampler;
uniform sampler2D FreakBlur;
uniform sampler2D FreakBlurDepth;

uniform vec4 ColorModulate;

in vec2 texCoord;

out vec4 fragColor;

float near = 0.001;
float far  = 1000.0;
float LinearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    float v = (near * far) / (far + near - z * (far - near));
    return v / 2.0f;
}

void main(){
    vec4 color = texture(DiffuseSampler, texCoord);
    float fragDepth = min(texture(TerrainDepthSampler, texCoord).x, texture(HandDepthSampler, texCoord).x);
    float distortDepth = texture(FreakBlurDepth, texCoord).x;
    if (distortDepth < fragDepth) {
        vec4 distortColor = texture(FreakBlur, texCoord);
        float a = distortColor.a;
        color = (color * (1 - a)) + (distortColor * (a));
    }
    fragColor = color * ColorModulate;
}
