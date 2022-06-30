#version 120

uniform sampler2D DiffuseSampler;

uniform vec2 OutSize;

uniform float blurRadius = 4.0;
uniform float waveStrength = 0.04;

varying vec2 texCoord;
varying vec2 oneTexel;

void main(){
    vec4 mainPixel = texture2D(DiffuseSampler, texCoord);

    gl_FragColor = vec4(mainPixel.rgb, 1.0);

    if (mainPixel.a > 0.1 && mainPixel.a < 1.0) {
        vec4 sum = mainPixel;
        float total_strength = 1.0;

        // For all pixels in radius
        for (float x = -blurRadius; x <= blurRadius; x += 1.0) {
            for (float y = -blurRadius; y <= blurRadius; y += 1.0) {
                // Get offset from water's alpha
                vec2 refraction = vec2(0.2-mainPixel.a, 0.2-mainPixel.a) * waveStrength;
                vec2 offset = oneTexel * vec2(x, y) + refraction;

                // Reverse offset if it puts us outside of water
                vec4 newPixel = texture2D(DiffuseSampler, texCoord + offset);
                if (newPixel.a < 0.1 || newPixel.a == 1.0){
                    offset = -offset;
                } 

                // Add to blur sum - pixels further from center count less
                float strength = 1.0 - length(vec2(x, y))/blurRadius;
                sum += strength * texture2D(DiffuseSampler, texCoord + offset);
                total_strength += strength;
            }
        }
        sum /= total_strength;
        
        gl_FragColor = vec4(sum.rgb, 1.0);
    }

}
