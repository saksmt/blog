const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const cssnano = require("cssnano");
const HTMLWebpackPlugin = require("html-webpack-plugin");
const CompressionPlugin = require('compression-webpack-plugin');
const merge = require("webpack-merge");

const commonConfig = {
    output: {
        filename: '[name]-[contenthash].js'
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
                        sourceMap: false
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
        }, {
            test: /\.(ttf|eot|svg|woff|woff2)(\?[\s\S]+)?$/,
            use: 'file-loader?name=fonts/[contenthash].[ext]',
        }, {
            test: /\.(jpe?g|png|gif)$/i,
            use: [
                'file-loader?name=images/[name]-[contenthash].[ext]',
                'image-webpack-loader'
            ]
        }]
    },
    plugins: [
        new HTMLWebpackPlugin({
            filename: "index.html",
            template: "src/main/html/index.html",
            inject: 'head',
            meta: {
                charset: 'UTF-8'
            }
        }),
        new MiniCssExtractPlugin({
            filename: './app-[contenthash].css',
            chunkFilename: './_[id]-[contenthash].css',
        })
    ]
};

const devConfig = {
    entry: {
        config: './src/main/js/default.dev.config.js',
        app: './src/main/js/index.js'
    },
    output: {
        path: path.resolve(__dirname, 'target', "web"),
        publicPath: "./"
    },
    devtool: 'inline-sourcemap'
};

const prodConfig = {
    entry: {
        config: './src/main/js/default.prod.config.js',
        app: './src/main/js/index.js'
    },
    output: {
        path: path.resolve(__dirname, 'target', "prod-web"),
        publicPath: '/'
    },
    optimization: {
        concatenateModules: true
    },
    plugins: [
        new OptimizeCSSAssetsPlugin({
            cssProcessor: cssnano,
            cssProcessorPluginOptions: {
                preset: ['default', { discardComments: { removeAll: true } }],
            },
            canPrint: true,
        }),
        new CompressionPlugin({
            filename: '[path].br',
            algorithm: 'brotliCompress',
            test: /\.(js|css|html|svg|woff|woff2|eot|ttf)$/,
            compressionOptions: { level: 11 },
            threshold: 10240,
            minRatio: 0.8,
            deleteOriginalAssets: false,
        }),
        new CompressionPlugin({
            filename: '[path].gz',
            algorithm: 'gzip',
            test: /\.(js|css|html|svg|woff|woff2|eot|ttf)$/,
            threshold: 10240,
            minRatio: 0.8,
            deleteOriginalAssets: false,
        })
    ],
    devtool: 'sourcemap'
};

module.exports = merge(
    commonConfig,
    process.env.NODE_ENV === 'production' ? prodConfig : devConfig
);
