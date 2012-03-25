package org.grails.twitter

import org.grails.twitte.auth.*

class Status {
    
    String message
    String author
    Date dateCreated
    
    static constraints = {
        message blank:false
        message size: 2..141    
    }
    
}
