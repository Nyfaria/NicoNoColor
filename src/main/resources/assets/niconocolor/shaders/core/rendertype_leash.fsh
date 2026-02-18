#version 150

#moj_import <fog.glsl>

uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
flat in vec4 vertexColor;

out vec4 fragColor;

void main() {
    float gray = 0.21 * vertexColor.r + 0.71 * vertexColor.g + 0.07 * vertexColor.b;
    fragColor = linear_fog(vec4(vertexColor.rgb * (1.0 - 1) + (gray * 1), vertexColor.a), vertexDistance, FogStart, FogEnd, FogColor);
}
