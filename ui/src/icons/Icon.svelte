<script lang="ts">
  import icons from './icons'

  export let name: string
  export let strokeWidth = '2'
</script>

{@html icons[name]?.replace('<svg', `<svg class="icon shrink-0 ${name} ${$$props.class ?? ''}" stroke-width=${strokeWidth} focusable="false"`) ?? 'NO-ICON-' + name}

<style>
  :global(.icon) {
    width: 1em;
    height: 1em;
  }
</style>
