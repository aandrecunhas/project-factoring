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

        then: "The percentage should be greater or equal 0 and less than or equal 100"
        baseRate.percentage >= 0 && baseRate.percentage <= 100

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


   void "Verify minimum and maximum revenue for right values"() {

        when: "Set right values for the fields max and min revenue"
        baseRate.maxRevenue = maxRevenue
        baseRate.minRevenue = minRevenue

        then: "Min and max revenue should be greater or equal to 0"
        baseRate.maxRevenue >= 0 && baseRate.minRevenue >=0

        where:
        maxRevenue << [5, 10, 1000, 4000, 5000, 3000]
        minRevenue << [5, 10, 500, 2000, 1000, 600]

    }


    void "Verify if max revenue is great than min revenue"() {

        when: "Set right values for the fields max and min revenue"
        baseRate.maxRevenue = maxRevenue
        baseRate.minRevenue = minRevenue

        then: "Max revenue should be great than min revenue"
        baseRate.maxRevenue >= baseRate.minRevenue

        where:
        maxRevenue << [5, 10, 1000, 4000, 5000, 3000]
        minRevenue << [5, 10, 500, 2000, 1000, 600]

    }

    void "Verify if max revenue less than min revenue fails"() {

        when: "Set wrong values for the fields max and min revenue"
        baseRate.maxRevenue = maxRevenue
        baseRate.minRevenue = minRevenue
        baseRate.save()

        then: "Max revenue should fail"
        baseRate.errors["maxRevenue"].codes.contains("range.toosmall")

        where:
        maxRevenue << [50, 99, 20, 105, 2000, 3503]
        minRevenue << [100, 105, 23, 210, 2001, 4000]

    }


}
