#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0) * vertexColor * ColorModulator;
    if (color.a < 0.1) {
        discard;
    }
    float gray = 0.21 * color.r + 0.71 * color.g + 0.07 * color.b;
    fragColor = vec4(color.rgb * (1.0 - 1) + (gray * 1), color.a) * linear_fog_fade(vertexDistance, FogStart, FogEnd);
}
