package se.rest

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application

@Suppress("unused")
@OpenAPIDefinition(info = Info(
        title = "SE Rest",
        version = "1.0"
))
class RestApplication : Application()
