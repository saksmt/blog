import '../scss/app/index.scss';
import { runScalaApp } from '../../../target/scala.js';
import { highlightBlock } from "./hl";

runScalaApp({ highlight: highlightBlock });
