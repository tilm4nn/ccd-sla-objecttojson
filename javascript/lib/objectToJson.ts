/*
 * The MIT License
 *
 * Copyright (C) 2011 Tilmann Kuhn
 *
 * http://www.object-zoo.net
 *
 * mailto:ccd-training@object-zoo.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

export class ObjectToJson {

    public objectToJson(object: Object): string {
        let json: string = '';
        json += '{\n';

        let propertySeparator: string = '';
        for (let prop in object) {
            let propertyValue: any = object[prop];
            let propertyValueJson: string;
            if (typeof propertyValue === 'undefined' || propertyValue === null) {
                propertyValueJson = 'null';
            } else if (typeof propertyValue === 'string') {
                propertyValueJson = `"${propertyValue}"`;
            } else if (typeof propertyValue === 'number') {
                propertyValueJson = propertyValue.toString();
            } else {
                propertyValueJson = this.objectToJson(propertyValue);
            }

            json += propertySeparator + prop + ': ' + propertyValueJson;
            propertySeparator = ',\n';
        }

        json += '}\n';
        return json;
    }

}
