package com.axus.winelore

import com.axus.id.model.value.AUID
import eth.likespro.commons.models.Pagination
import eth.likespro.lpfcp.LPFCP

fun main() {
    println("Hello Nazik!")

    // Here we initialize an LPFCP remote server ("processor") with the server Interface (WineLoreEndpoint) and the server's LPFCP Endpoint URI
    val wineloreServer = LPFCP.getProcessor<WineLoreEndpoint>("http://months-demonstration.gl.at.ply.gg:26709/lpfcp")

    // The next line of code will fetch all commissions in which likespro participated
    val likesproCommissions = wineloreServer.getCommissionsByParticipant(AUID(value = 1), Pagination(page = 0, itemsPerPage = 1000))

    // We can just print all the commissions in raw format using println() because every data structure in the API is Serializable
    println("Likespro Commissions: $likesproCommissions")

    // ...or in a much prettier way
    likesproCommissions.forEach { (commission, participant) ->
        println("${participant.role} in Commission ${commission.name.value}")
    }

    // As you could note, every value is wrapped in the corresponding Value class, e.g., AUID is wrapped in AUID Value class.
    // So you must wrap the values in a form <Value_class>(<value>) and access the values as <wrapped_value>.value.
    // For example, to use Commission name "La Commission" in a call, we must wrap it like `Commission.Name("La Commission")`.
    // And we can access then the original value with `Commission.Name("La Commission").value`
}