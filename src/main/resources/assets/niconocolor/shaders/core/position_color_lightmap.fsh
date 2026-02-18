#version 150

uniform sampler2D Sampler2;

uniform vec4 ColorModulator;

in vec4 vertexColor;
in vec2 texCoord2;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler2, texCoord2) * vertexColor;
    float gray = 0.21 * color.r + 0.71 * color.g + 0.07 * color.b;
    fragColor = vec4(color.rgb * (1.0 - 1) + (gray * 1), color.a) * ColorModulator;
}
