package pl.cezarysanecki.templateforspringboot.samplepackage

import pl.cezarysanecki.templateforspringboot.AbstractSpec

class SampleSpec extends AbstractSpec {

    def "simple test"() {
        given:
        int a = 2
        int b = 3

        when:
        int result = a + b

        then:
        result == 5
    }

}
