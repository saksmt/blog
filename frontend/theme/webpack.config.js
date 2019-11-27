const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: './src/index.js',
    mode: 'development',
    output: {
        path: path.resolve(__dirname, 'target'),
        filename: 'app.js'
    },
    module: {
        rules: [{
            test: /\.scss|\.sass|\.css$/,
            use: [
                {
                    loader: MiniCssExtractPlugin.loader
                },
                {
                    loader: "css-loader",
                    options: {
                        sourceMap: true
                    }
                },
                "resolve-url-loader",
                {
                    loader: "sass-loader",
                    options: {
                        sourceMap: true
                    }
                }
            ]
        }, {
            test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
            use: 'url-loader?limit=10000',
        },
        {
            test: /\.m?js$/,
            exclude: /(node_modules|bower_components)/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env']
                }
            }
        },
        {
            test: /\.(ttf|eot|svg|woff|woff2)(\?[\s\S]+)?$/,
            use: 'file-loader?name=fonts/[contenthash].[ext]',
        },
        {
            test: /\.(jpe?g|png|gif)$/i,
            use: [
                'file-loader?name=images/[name].[ext]',
                'image-webpack-loader'
            ]
        }]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: './[name].css',
            chunkFilename: './[id].css',
        }),
    ],
    devtool: 'inline-sourcemap'
};
