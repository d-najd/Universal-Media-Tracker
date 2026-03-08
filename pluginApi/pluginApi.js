class addonBuilder {
    constructor(settings) {
        this.settings = settings

        if (typeof this.settings.id !== "string") {
            throw Error("Invalid id")
        }

        // const semanticVersionRegex = /^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)$/;
        // if (!semanticVersionRegex.test(this.settings.version) && typeof this.settings.version !== "string") {
        //     throw Error("Invalid version")
        // }
        if (typeof this.settings.version !== "string") {
            throw Error("Invalid version")
        }

        if (typeof this.settings.name !== "string") {
            throw Error("Invalid name")
        }

        if (this.settings.description !== undefined && typeof this.settings.description !== "string") {
            throw Error("Invalid Description")
        }
    }

    defineCatalogHandler(callback) {
        this.catalogHandler = { }
        this.catalogMethod = callback
    }

    /*
    defineCatalogHandler(function (args, cb) {
        return catalogHandler(args, cb)
    })
     */

    // defineCatalogHandler(args) {
        // this.catalogHandler = catalogSettings
    // }
}

let builder = new addonBuilder({
    id: "com.test",
    version: "1.1.1A",
    name: "Hello",
})

builder.defineCatalogHandler(function (args) {
    return 1
})

function build() {
    return builder
}