import {ObjectToJson} from '../lib/objectToJson';
import * as chai from 'chai';
const expect = chai.expect;

describe('ObjectToJsonTest', () => {

    let sut: ObjectToJson = new ObjectToJson();

    it('should serialize string property', () => {
        let bean: any = {
            stringProperty: 'testString'
        }

        let json: string = sut.objectToJson(bean);
        json = json.replace(/\s/g, '');
        expect(json).to.eq('{stringProperty:"testString"}');
    });

    it('should serialize double property', () => {
        let bean: any = {
            doubleProperty: 1.2345
        }

        let json: string = sut.objectToJson(bean);
        json = json.replace(/\s/g, '');
        expect(json).to.eq('{doubleProperty:1.2345}');
    });

    it('should serialize null property', () => {
        let bean: any = {
            nullProperty: null
        }

        let json: string = sut.objectToJson(bean);
        json = json.replace(/\s/g, '');
        expect(json).to.eq('{nullProperty:null}');
    });

    it('should serialize Bean property', () => {
        let bean: any = {
            beanProperty: {
                stringProperty: 'testString'
            }
        }

        let json: string = sut.objectToJson(bean);
        json = json.replace(/\s/g, '');
        expect(json).to.eq('{beanProperty:{stringProperty:"testString"}}');
    });

})