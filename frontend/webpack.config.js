const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const cssnano = require("cssnano");
const HTMLWebpackPlugin = require("html-webpack-plugin");
const CompressionPlugin = require('compression-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');

const devConfig = {
    entry: './src/main/js/index.js',
    mode: 'development',
    output: {
        path: path.resolve(__dirname, 'target', "web"),
        filename: 'app.js',
        devtoolModuleFilenameTemplate: (f) => {
            if (f.resourcePath.startsWith("http://") ||
                f.resourcePath.startsWith("https://") ||
                f.resourcePath.startsWith("file://")) {
                return f.resourcePath;
            } else {
                return "webpack://" + f.namespace + "/" + f.resourcePath;
            }
        }
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
                exclude: /(node_modules|bower_components|scala.js)/,
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
            },
            {
                test: /scala\.js$/,
                use: path.resolve(__dirname, 'scala-sourcemaps.js'),
                enforce: "pre"
            }]
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: './[name].css',
            chunkFilename: './[id].css',
        }),
        new CopyPlugin([
            { from: 'src/main/js/default.dev.config.js', to: './config.js' }
        ])
    ],
    devtool: 'inline-sourcemap'
};

const prodConfig = {
    entry: './src/main/js/index.js',
    mode: 'production',
    output: {
        path: path.resolve(__dirname, 'target', "prod-web"),
        filename: 'app-[contenthash].js',
        publicPath: '/'
    },
    optimization: {
        minimizer: [
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
        ],
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
            filename: 'br/[path]',
            algorithm: 'brotliCompress',
            test: /\.(js|css|html|svg)$/,
            compressionOptions: { level: 11 },
            threshold: 10240,
            minRatio: 0.8,
            deleteOriginalAssets: false,
        }),
        new CompressionPlugin({
            filename: 'gz/[path]',
            algorithm: 'gzip',
            test: /\.(js|css|html|svg)$/,
            threshold: 10240,
            minRatio: 0.8,
            deleteOriginalAssets: false,
        }),
        new CopyPlugin([
            { from: 'src/main/js/default.prod.config.js', to: './config.js' }
        ])
    ],
    devtool: 'sourcemap'
};

module.exports = process.env.NODE_ENV === 'production' ? prodConfig : devConfig;
