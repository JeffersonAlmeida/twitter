import org.grails.twitter.auth.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        if (!Person.count()) {
            createData()
        }
        def p = Person.get(1)
        System.out.println("Nome Usuário: " +  p.username);
        System.out.println("Nome senha: " +  p.password);
    }

    def destroy = {
    }

    private void createData() {
        def userRole = new Authority(authority: 'ROLE_USER').save()

        String password = springSecurityService.encodePassword('password')

        [jeff: 'Jeff Brown', graeme: 'Graeme Rocher', burt: 'Burt Beckwith', peter: 'Peter Ledbrook'].each { userName, realName ->
            def user = new Person(username: userName, realName: realName, password: password, enabled: true).save()
            PersonAuthority.create user, userRole, true
        }
    }
}
