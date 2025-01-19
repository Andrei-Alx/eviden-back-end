import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 100,
    duration: '5m',
    cloud: {
        projectID: 3739129,
        name: 'ApiGateway Load Test'
    }
};

export default function() {
    http.get('http://34.91.246.205:80/swagger/index.html');
    sleep(1);
}