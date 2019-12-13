import { highlight } from 'highlight.js/lib/highlight'
import { nodeStream, mergeStreams } from './_copy-paste';

export const highlightCode = (block, language, tabSize) => {
    const tabReplacement = ' '.repeat(tabSize);
    const text = block.textContent;
    const result = highlight(language, text, true);
    const originalStream = nodeStream(block);

    if (originalStream.length > 0) {
        console.log("HIGHLIGHTING", block, language);
        const resultNode = document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
        resultNode.innerHTML = result.value;

        result.value =
            mergeStreams(originalStream, nodeStream(resultNode), text)
                .replace(/\t/g, tabReplacement);
    }

    block.innerHTML = result.value;
    block.className = 'hljs';
    block.result = {
        language: result.language,
        re: result.relevance
    };
};

export const highlightBlock = (block, lang, tabsize) => {
    const actualLang = orElse(lang, block.getAttribute('lang'));
    const actualTabsize = orElse(tabsize, block.dataset.tabsize, 2);
    const actualBlock = block.querySelector('pre');

    if (nonNull(actualBlock) && nonNull(actualLang)) {
        highlightCode(actualBlock, actualLang, actualTabsize);
    } else {
        console.error("expected block and language to be present, got: block={} lang={}", actualBlock, actualLang);
    }
};

const nonNull = x => x !== undefined && x !== null;
const orElse = (arg, ...args) => args.reduce((a, b) => nonNull(a) ? a : b, arg);
