<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<main class="text-center pt-10">
  <h1>{t.title}</h1>
</main>
