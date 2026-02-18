#version 150

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a == 0.0) {
        discard;
    }
    float gray = 0.21 * ColorModulator.r + 0.71 * ColorModulator.g + 0.07 * ColorModulator.b;
    fragColor = vec4(vec4(ColorModulator.rgb * (1.0 - 1) + (gray * 1), ColorModulator.a).rgb * vertexColor.rgb, ColorModulator.a);
}
