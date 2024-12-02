import 'src/extensions/ArrayExtensions'

// not provided by jsdom
Element.prototype.animate = (() => ({cancel: () => {}})) as any
