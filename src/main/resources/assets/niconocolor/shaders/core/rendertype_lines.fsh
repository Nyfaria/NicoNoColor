#version 150

#moj_import <fog.glsl>

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    vec4 color = vertexColor * ColorModulator;
    float gray = 0.21 * color.r + 0.71 * color.g + 0.07 * color.b;
    fragColor = linear_fog(vec4(color.rgb * (1.0 - 1) + (gray * 1), color.a), vertexDistance, FogStart, FogEnd, FogColor);
}
