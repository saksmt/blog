import hljs from 'highlight.js/lib/highlight'

import javascript from 'highlight.js/lib/languages/javascript'
import haskell from 'highlight.js/lib/languages/haskell'
import scala from 'highlight.js/lib/languages/scala'
import nix from 'highlight.js/lib/languages/nix'
import yaml from 'highlight.js/lib/languages/yaml'
import bash from 'highlight.js/lib/languages/bash'
import sql from 'highlight.js/lib/languages/sql'
import dockerfile from 'highlight.js/lib/languages/dockerfile'

hljs.registerLanguage('javascript', javascript);
hljs.registerLanguage('haskell', haskell);
hljs.registerLanguage('scala', scala);
hljs.registerLanguage('nix', nix);
hljs.registerLanguage('yaml', yaml);
hljs.registerLanguage('bash', bash);
hljs.registerLanguage('sql', sql);
hljs.registerLanguage('dockerfile', dockerfile);

export {
    hljs
};