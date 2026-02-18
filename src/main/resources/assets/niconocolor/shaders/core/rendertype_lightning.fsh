#version 150

#moj_import <fog.glsl>

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;

in float vertexDistance;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    float gray = 0.21 * ColorModulator.r + 0.71 * ColorModulator.g + 0.07 * ColorModulator.b;
    fragColor = vertexColor * vec4(ColorModulator.rgb * (1.0 - 1) + (gray * 1), ColorModulator.a) * linear_fog_fade(vertexDistance, FogStart, FogEnd);
}
