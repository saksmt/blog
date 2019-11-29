const fs = require("fs");
const path = require("path");

module.exports = function (source) {
    const sourceWithoutMap = source.split("\n")
        .filter(it => !it.includes("//# sourceMappingURL="))
        .join("\n");
    const map = source.split("\n")
        .filter(it => it.includes("//# sourceMappingURL="))
        .map(it => it.trim().substring("//# sourceMappingURL=".length).trim())
        .map(it => JSON.parse(fs.readFileSync(path.resolve(__dirname, "target", it), {"encoding": "utf-8"}).toString()))
        .map(it => {
            it.sources = it.sources.map(mapPath => {
                if (mapPath.startsWith("http://") || mapPath.startsWith("https://")) {
                    return mapPath;
                } else {
                    return "file://" + path.resolve(mapPath);
                }
            });

            return it
        })[0];

    this.callback(null, sourceWithoutMap, (map === undefined || map === null) ? undefined : JSON.stringify(map))
};