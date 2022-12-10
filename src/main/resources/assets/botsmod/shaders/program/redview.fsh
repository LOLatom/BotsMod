#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulate;

in vec2 texCoord;

out vec4 fragColor;

void main(){
    vec4 color = texture(DiffuseSampler, texCoord) * ColorModulate;
    fragColor = vec4(color.rgb + (vec3(64,43,44) / 166), color.a);
}
