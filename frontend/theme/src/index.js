import './app/index.scss';
import { highlightBlock } from "./hljs";

const initiallyHighlightBlocks = () => document.querySelectorAll(".codeblock code").forEach(block => highlightBlock(block));

addEventListener('DOMContentLoaded', initiallyHighlightBlocks, false);
addEventListener('load', initiallyHighlightBlocks, false);
