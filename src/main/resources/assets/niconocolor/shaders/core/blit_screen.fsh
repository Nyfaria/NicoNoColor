#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulator;

in vec2 texCoord;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    vec4 color = texture(DiffuseSampler, texCoord) * vertexColor;
    float gray = 0.21 * color.r + 0.71 * color.g + 0.07 * color.b;
    fragColor = vec4(color.rgb * (1.0 - 1) + (gray * 1), color.a) * ColorModulator;
}
