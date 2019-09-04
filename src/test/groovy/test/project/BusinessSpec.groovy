package test.project

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BusinessSpec extends Specification implements DomainUnitTest<Business> {

    Business business = new Business()

    def setup() {
    }

    def cleanup() {
    }

    void "Test if wrong attribute in business class will fail"() {

        when: "Set wrong values for business' fields"
        business.name = name
        business.email = email
        business.enterpriseName = enterpriseName
        business.monthlyRevenue = monthlyRevenue
        business.save()

        then: "Business attribute should have some errors"
        business.errors[wrongAtribute]?.codes?.contains(expectedError)

        where:
        name | email | enterpriseName | monthlyRevenue | wrongAtribute | expectedError
        "André Cunha André Cunha André Cunha André Cunha André Cunha André Cunha" | "aandrecunhas@gmail.com" | "Andre Moveis" | 50000 | "name" | "maxSize.exceeded"
        "Derick Rosa" | "derickrosa.gmail.com" | "Derick Calçados" | 70000 | "email" | "email.invalid"
        "Jonas Cunha" | "jonascunha@gmail.com" | "Jonas Joias Jonas Joias Jonas Joias Jonas Joias Jonas Joias Jonas Joias" | 100000 | "enterpriseName" | "maxSize.exceeded"
        "Mikael Lima" | "mikaellima@gmail.com" | "Mikael Artigos de Pescaria" | 100 | "monthlyRevenue" | "business.monthlyRevenue.range.error"

    }

    void "Test attribute of business"() {

        when: "Set values for business' fields"
        business.name = name
        business.email = email
        business.enterpriseName = enterpriseName
        business.monthlyRevenue = monthlyRevenue
        business.save()

        then: "Business should have no errors"
        !business.hasErrors()

        where:
        name | email | enterpriseName | monthlyRevenue
        "André Cunha" | "aandrecunhas@gmail.com" | "Andre Moveis" | 50000
        "Derick Rosa" | "derickrosa@gmail.com" | "Derick Calçados" | 70000
        "Jonas Cunha" | "jonascunha@gmail.com" | "Jonas Joias" | 100000
        "Mikael Lima" | "mikaellima@gmail.com" | "Mikael Artigos de Pescaria" | 35000

    }

    void "Test if base rate is compatible with business revenue"(){
        when: "Set attributes and base rate for business "
        business.name = "André Cunha"
        business.email = "aandrecunhas@gmail.com"
        business.enterpriseName = "André Móveis"
        business.monthlyRevenue = monthlyRevenue
        business.baseRate = new BaseRate(percentage: 1.65, minRevenue: 5000, maxRevenue: 15000)
        business.save()

        then: "business and base rate should have no errors"
        !business.hasErrors()

        where:
        monthlyRevenue << [10000, 20000, 30000, 40000, 50000]
    }

    void "Test if monthly revenue and base rate min and max revenue fails"() {

        when: "Set values for business and out of range monthly revenue"
        business.name = "André Cunha"
        business.email = "aandrecunhas@gmail.com"
        business.enterpriseName = "André Móveis"
        business.monthlyRevenue = monthlyRevenue
        business.baseRate = new BaseRate(percentage: percentage, minRevenue: minRevenue, maxRevenue: maxRevenue)
        business.save()

        then: "Business should have monthly revenue errors"
        business.errors['baseRate']?.codes?.contains(expectedError)

        where:
        monthlyRevenue | percentage | minRevenue | maxRevenue | expectedError
        1000 | 1.65 | 5000 | 10000 | "revenue.tooSmall"
        25000 | 1.65 | 10000 | 20000 | "revenue.tooBig"
    }

}
