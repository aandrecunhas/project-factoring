package test.project

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BusinessServiceSpec extends Specification {

    BusinessService businessService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Business(...).save(flush: true, failOnError: true)
        //new Business(...).save(flush: true, failOnError: true)
        //Business business = new Business(...).save(flush: true, failOnError: true)
        //new Business(...).save(flush: true, failOnError: true)
        //new Business(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //business.id
    }

    void "test get"() {
        setupData()

        expect:
        businessService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Business> businessList = businessService.list(max: 2, offset: 2)

        then:
        businessList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        businessService.count() == 5
    }

    void "test delete"() {
        Long businessId = setupData()

        expect:
        businessService.count() == 5

        when:
        businessService.delete(businessId)
        sessionFactory.currentSession.flush()

        then:
        businessService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Business business = new Business()
        businessService.save(business)

        then:
        business.id != null
    }
}
