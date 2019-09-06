package test.project

class BootStrap {

    def init = { servletContext ->
        if(BaseRate.count() == 0) {
            new BaseRate(percentage: 1.8, minRevenue: 0, maxRevenue: 49999).save()
            new BaseRate(percentage: 1.65, minRevenue: 50000, maxRevenue: 100000).save()
            new BaseRate(percentage: 1.5, minRevenue: 100001, maxRevenue: 100000000).save()
        }
    }

    def destroy = {
    }
}
