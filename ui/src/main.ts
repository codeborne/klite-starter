import {mount} from 'svelte'
import './extensions/ArrayExtensions'
import './global.css'
import App from './App.svelte'
import {initErrorHandlers} from 'src/api/errorHandlers'

initErrorHandlers()

const app = mount(App, {
  target: document.getElementById('app')!,
})
