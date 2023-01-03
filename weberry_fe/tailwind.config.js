/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
  ],

  theme: {
    fill: theme => ({
      'red': theme('colors.red.500'),
      'green': theme('colors.green.500'),
      'blue': theme('colors.blue.500'),
    }),

    colors: {
      transparent: 'transparent',
      current: 'currentColor',
      'white': '#ffffff',
      'grey': "#d3d3d3",
      'light_pink': "#ff8468",
      'black_600' :"#4B5563",
      'yello_100' : "#FDF6B2",
      'berry' : "#ff0033",
      "sun": "#FFA778",
      "water":"#50c2ff",
    },
    extend: {

    },
  },
  variants: {
    fill: ['hover', 'focus'],
  },
  plugins: [],
}