#version 120
attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;
    gl_Position = u_projTrans * a_position;
}

    //-!@#frag
    #version 120
varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

uniform float blinkAlpha = 0.0f;
float blinkIntensity = 4.0;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords).rgba;
    color = vec4(color.r*(1.0+(blinkIntensity*blinkAlpha)), color.g*(1.0+(blinkIntensity*blinkAlpha)), color.b*(1.0+(blinkIntensity*blinkAlpha)), color.a);

    gl_FragColor = vec4(color);
}