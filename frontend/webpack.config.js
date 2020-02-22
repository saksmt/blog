const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const cssnano = require("cssnano");
const HTMLWebpackPlugin = require("html-webpack-plugin");
const CompressionPlugin = require('compression-webpack-plugin');

const devConfig = {
    entry: {
        config: './src/main/js/default.dev.config.js',
        app: './src/main/js/index.js'
    },
    mode: 'development',
    output: {
        path: path.resolve(__dirname, 'target', "web"),
        filename: '[name].js',
        publicPath: './'
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
        new MiniCssExtractPlugin({
            filename: './[name].css',
            chunkFilename: './[id].css',
        }),
        new HTMLWebpackPlugin({
            filename: "index.html",
            template: "src/main/html/index.html",
            inject: 'head',
            meta: {
                charset: 'UTF-8'
            }
        })
    ],
    devtool: 'inline-sourcemap'
};

const prodConfig = {
    entry: {
        config: './src/main/js/default.prod.config.js',
        app: './src/main/js/index.js'
    },
    mode: 'production',
    output: {
        path: path.resolve(__dirname, 'target', "prod-web"),
        filename: '[name]-[contenthash].js',
        publicPath: '/'
    },
    optimization: {
        /*minimizer: [
            new TerserPlugin({
                terserOptions: {
                    mangle: {
                        toplevel: true,
                        properties: { keep_quoted: "strict" },
                        reserved: ["appConfig.routingType", "appConfig.baseUri"]
                    },
                    compress: {
                        passes: 2,
                        toplevel: true
                    },
                    output: {
                        comments: false
                    },
                    toplevel: true
                }
            })
        ],*/
        concatenateModules: true
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
        new MiniCssExtractPlugin({
            filename: './app-[contenthash].css',
            chunkFilename: './_[id]-[contenthash].css',
        }),
        new OptimizeCSSAssetsPlugin({
            cssProcessor: cssnano,
            cssProcessorPluginOptions: {
                preset: ['default', { discardComments: { removeAll: true } }],
            },
            canPrint: true,
        }),
        new HTMLWebpackPlugin({
            filename: "index.html",
            template: "src/main/html/index.html",
            inject: 'head',
            meta: {
                charset: 'UTF-8'
            }
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

module.exports = process.env.NODE_ENV === 'production' ? prodConfig : devConfig;
