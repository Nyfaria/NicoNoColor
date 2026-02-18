#version 150

uniform vec4 ColorModulator;

out vec4 fragColor;

void main() {
    float gray = 0.21 * ColorModulator.r + 0.71 * ColorModulator.g + 0.07 * ColorModulator.b;
    fragColor = vec4(ColorModulator.rgb * (1.0 - 1) + (gray * 1), ColorModulator.a);
}
