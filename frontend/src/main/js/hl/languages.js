import { registerLanguage } from 'highlight.js/lib/highlight'

import javascript from 'highlight.js/lib/languages/javascript'
import haskell from 'highlight.js/lib/languages/haskell'
import scala from 'highlight.js/lib/languages/scala'
import nix from 'highlight.js/lib/languages/nix'
import yaml from 'highlight.js/lib/languages/yaml'
import bash from 'highlight.js/lib/languages/bash'
import sql from 'highlight.js/lib/languages/sql'
import dockerfile from 'highlight.js/lib/languages/dockerfile'

registerLanguage('javascript', javascript);
registerLanguage('haskell', haskell);
registerLanguage('scala', scala);
registerLanguage('nix', nix);
registerLanguage('yaml', yaml);
registerLanguage('bash', bash);
registerLanguage('sql', sql);
registerLanguage('dockerfile', dockerfile);