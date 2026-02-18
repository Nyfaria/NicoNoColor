#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform mat4 ProjMat;
uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    color *= vertexColor * ColorModulator;
    float fragmentDistance = -ProjMat[3].z / ((gl_FragCoord.z) * -2.0 + 1.0 - ProjMat[2].z);
    float gray = 0.21 * color.r + 0.71 * color.g + 0.07 * color.b;
    fragColor = linear_fog(vec4(color.rgb * (1.0 - 1) + (gray * 1), color.a), fragmentDistance, FogStart, FogEnd, FogColor);
}
