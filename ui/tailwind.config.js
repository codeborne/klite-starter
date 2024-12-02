import colors from 'tailwindcss/colors'
import forms from '@tailwindcss/forms'

export default {
  content: ["./src/**/*.svelte"],
  darkMode: 'class', // or 'media' or 'class'
  theme: {
    extend: {
      fontFamily: {
        sans: 'sans-serif',
        serif: 'serif'
      },
      colors: {
        'success': colors.emerald,
        'warning': colors.amber,
        'danger': colors.red,
        'primary': colors.blue,
        'secondary': colors.gray
      },
    },
  },
  plugins: [
    forms,
  ]
}
