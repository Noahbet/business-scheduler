/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        maroon: {
          100: '#A24857',
          200: '#800000', 
        },
      }
    },
  },
  plugins: [],
}