package org.grails.twitter

import org.grails.twitter.auth.Person;

class StatusController {
    
    def springSecurityService
    def index() {   
        def messages = currentUserTimeline()  
        [statusMessages: messages]
    }
    
     def updateStatus(){        
           def status = new Status(message: params.message)
           def p = Person.get(springSecurityService.principal.id)
           status?.author = p.username                          
           status.save()
           def messages = currentUserTimeline()       
           // render each item in the collection using the specified template
           render template: 'messages', collection: messages, var: 'statusMessage'
     }
    
    def follow(){
        print "Follow"
        def per = Person.get(params.id)
        if(per){
            def currentUser = lookUpPerson()
            currentUser.addToFollowed(per)
            currentUser.save()
        }
        redirect action: 'index'
    }
   
    private currentUserTimeline(){
        def menssages =  msgs()      
        if(!menssages){
           createMsg()
           menssages =  msgs()
        }      
        return menssages
    }
    
    def private msgs(){
        def s = Status.createCriteria()
        def per = lookUpPerson()
        def menssages = s{
            or{
                eq("author","$springSecurityService.principal.username")
                if(per.followed){
                    inList 'author', per.followed.username
                }
            }
            maxResults(10)
            order("dateCreated", "desc")
        }  
        return menssages 
    }
    
    def private createMsg(){
           def status = new Status(message: "Default")
           def p = Person.get(springSecurityService.principal.id)
           status?.author = p.username
           status.save()
    }
    
    def private lookUpPerson(){
        Person.get(springSecurityService.principal.id)
    }
    
}
