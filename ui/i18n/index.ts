import type en from './en.json'
import langs from './langs.json'

export function changeLang(lang: typeof langs[number]) {
  localStorage['lang'] = lang
  location.reload()
}

function choosePreferredLang() {
  let lang = localStorage['lang'] ?? navigator.language.split('-')[0]
  return langs.includes(lang) ? lang : langs[0]
}

async function load() {
  if (lang === 'en') return (await import('./en.json')).default
  else throw new Error('Unsupported lang: ' + lang)
}

export const lang = choosePreferredLang()
export let t: typeof en = await load()
