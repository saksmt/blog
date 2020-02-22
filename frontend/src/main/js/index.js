import '../scss/app/index.scss';
import '../../../target/scala.js';
import { highlightBlock } from "./hl";

window.runScalaApp({ highlight: highlightBlock }, {
    // todo: temporary solution probably need to pass raw string with HOCON or JSON and parse it inside, otherwise it gets optimized and broken
    // todo: also need to invent something for absolute/relative uri-s
    routingType: window.appConfig["routingType"],
    baseUri: window.appConfig["baseUri"]
});
