const path = require("path");
const WEB_RESOURCE_KEY = "workgroup-client-resources";

class BuildAtlasPluginWebResource {
  apply(compiler) {
    compiler.hooks.emit.tapAsync(
      "BuildAtlasWebResourcePlugin",
      (compilation, callback) => {
        const xmlArr = [];
        xmlArr.push(`<?xml version="1.0" encoding="UTF-8"?>`);
        xmlArr.push(`<bundles>`);
        xmlArr.push(`  <web-resource key="${WEB_RESOURCE_KEY}" name="Workgroup Client Resources">`);
        for (let fileName in compilation.assets) {
          xmlArr.push(`    <resource name="${fileName}" type="download" location="/client/${fileName}" />`);
        }
        xmlArr.push(`    <context>${WEB_RESOURCE_KEY}</context>`);
        xmlArr.push(`  </web-resource>`);
        xmlArr.push(`</bundles>`);
        const xml = xmlArr.join("\n");
        console.log(xml);
        compilation.assets[
          "../META-INF/plugin-descriptor/webpack-manifest.xml"
        ] = {
          source: function() {
            return xml;
          },
          size: function() {
            return xml.length;
          },
        };
        callback();
      }
    );
  }
}

// console.log("process.env:", process.env);
const isBuildLifecycle = process.env.npm_lifecycle_event.startsWith("build-");

module.exports = {
  devServer: {
    host: "0.0.0.0",
    port: 3000,
    proxy: {
      '^/jira/rest': {
        target: 'http://localhost:2990',
        changeOrigin: true
      },
    },
  },
  transpileDependencies: ["vuetify"],
  publicPath: "/",
  outputDir: path.resolve(__dirname, "../resources/client"),
  /*
  chainWebpack: config => {
    if(config.plugins.has('extract-css')) {
      const extractCSSPlugin = config.plugin('extract-css')
      extractCSSPlugin && extractCSSPlugin.tap(() => [{
        filename: 'css/[name].css',
        chunkFilename: 'css/[name].css'
      }])
    }
  },
  */
  configureWebpack: {
    plugins: isBuildLifecycle ? [new BuildAtlasPluginWebResource()] : [],
    /*
    output: {
      filename: 'js/[name].js',
      chunkFilename: 'js/[name].js'
    },
    */
  },
};
