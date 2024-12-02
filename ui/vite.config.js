import {defineConfig} from 'vite'
import {svelte} from '@sveltejs/vite-plugin-svelte'
import {svelteTesting} from '@testing-library/svelte/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [svelte(), svelteTesting()],
  resolve: {
    alias: {
      src: new URL('src', import.meta.url).pathname,
      i18n: new URL('i18n', import.meta.url).pathname
    },
  },
  server: {
    port: 8000,
    proxy: {
      '/api': 'http://localhost:8080',
    }
  },
  build: {
    outDir: 'build',
    target: 'es2022',
  },
  test: {
    globals: true,
    environment: 'jsdom',
  }
})
