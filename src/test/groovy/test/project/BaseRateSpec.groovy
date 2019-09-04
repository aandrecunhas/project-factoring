package test.project

import grails.gorm.validation.exceptions.ValidationConfigurationException
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class BaseRateSpec extends Specification implements DomainUnitTest<BaseRate> {

    BaseRate baseRate = new BaseRate()

    def setup() {
    }

    def cleanup() {
    }

    void "Verify values 1 to 100 for percentage field"() {

        when: "Values from 0 to 100 are set"
        baseRate.percentage = percentage

        then: "The percentage field is valid"
        baseRate.validate(['percentage'])

        where:
        percentage << [0, 10, 13, 43, 50, 95, 99, 100]
    }

    void "Verify wrong values for percentage field"() {

        when: "A wrong value less than 0 or greater than 100 is set for the field percentage"
        baseRate.percentage = percentage

        then: "The percentage field should be invalid"
        !baseRate.validate(['percentage'])

        where:
        percentage << [-10, -15, -20, -35, -100, -150]
    }


   void "Verify minimum and maximum revenue for right values"(){

        when: "Set right values for the fields max and min revenue"
        baseRate.maxRevenue = maxRevenue
        baseRate.minRevenue = minRevenue

        then: "Min and max revenue should be greater or equal to 0 and saved"
        baseRate.validate()

        where:
        maxRevenue << [5]
        minRevenue << [5]

    }


}
