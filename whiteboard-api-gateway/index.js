// var monitor = require('node-docker-monitor');
// var http = require('http');
// var httpProxy = require('http-proxy');
// var parseurl = require('parseurl');
//
// // process input via env vars
// var dockerOpts = { socketPath: process.env.DOCKER_SOCKET };
// if (!dockerOpts.socketPath) {
//     dockerOpts.host = process.env.DOCKER_HOST;
//     dockerOpts.port = process.env.DOCKER_PORT;
//     if (!dockerOpts.host) {
//         dockerOpts.socketPath = '/var/run/docker.sock';
//     }
// }
// var httpPort = process.env.HTTP_HOST || 8000;
//
// // available routes collection
// var routes = {
//     // '303c56be38b748576be1598eb9d6a746fb2792c5c9c6d83608ed8f2b5501b063' : {
//     //     apiRoute: '/service1',
//     //     upstreamUrl: 'http://127.0.0.1:8887'
//     // }
// };
//
// console.log('Connecting to Docker: %j', dockerOpts);
//
// monitor({
//     onContainerUp: function (containerInfo, docker) {
//         if (containerInfo.Labels && containerInfo.Labels.api_route) {
//             // register a new route if container has "api_route" label defined
//             var container = docker.getContainer(containerInfo.Id);
//             // get running container details
//             container.inspect(function (err, containerDetails) {
//                 if (err) {
//                     console.log('Error getting container details for: %j', containerInfo, err);
//                 } else {
//                     try {
//                         // prepare and register a new route
//                         var route = {
//                             apiRoute: containerInfo.Labels.api_route,
//                             upstreamUrl: getUpstreamUrl(containerDetails)
//                         };
//
//                         routes[containerInfo.Id] = route;
//                         console.log('Registered new api route: %j', route);
//                     } catch (e) {
//                         console.log('Error creating new api route for: %j', containerDetails, e);
//                     }
//                 }
//             });
//         }
//     },
//
//     onContainerDown: function (container) {
//         if (container.Labels && container.Labels.api_route) {
//             // remove existing route when container goes down
//             var route = routes[container.Id];
//             if (route) {
//                 delete routes[container.Id];
//                 console.log('Removed api route: %j', route);
//             }
//         }
//     }
// }, dockerOpts);
//
// // create and start http server
// var server = http.createServer(function (req, res) {
//     for (id in routes) {
//         if (routes.hasOwnProperty(id) && handleRoute(routes[id], req, res)) {
//             return;
//         }
//     }
//
//     returnError(req, res);
// });
//
// console.log('API gateway is listening on port: %d', httpPort);
// server.listen(httpPort);
//
// // create proxy
// var proxy = httpProxy.createProxyServer();
// proxy.on('error', function (err, req, res) {
//     console.log(err);
//     returnError(req, res);
// });
//
// // proxy HTTP request / response to / from destination upstream service if route matches
// function handleRoute(route, req, res) {
//     var url = req.url;
//     var parsedUrl = parseurl(req);
//
//     if (parsedUrl.path.indexOf(route.apiRoute) === 0) {
//         req.url = url.replace(route.apiRoute, '');
//         proxy.web(req, res, { target: route.upstreamUrl });
//         return true;
//     }
// }
//
// // generate upstream url from containerDetails
// function getUpstreamUrl(containerDetails) {
//     var ports = containerDetails.NetworkSettings.Ports;
//     for (id in ports) {
//         if (ports.hasOwnProperty(id)) {
//             return 'http://' + containerDetails.NetworkSettings.IPAddress + ':' + id.split('/')[0];
//         }
//     }
// }
//
// // send 502 response to the client in case of an error
// function returnError(req, res) {
//     console.log(req.url);
//     res.writeHead(502, {'Content-Type': 'text/plain'});
//     res.write('Bad Gateway for: ' + req.url);
//     res.end();
// }



var http = require('http');
var httpProxy = require('http-proxy');
var parseurl = require('parseurl');
var httpPort = process.env.HTTP_HOST || 8000;

// available routes collection
var routes =
{
    '303c56be38b748576be1598eb9d6a746fb2792c5c9c6d83608ed8f2b5501b063' :
    {
        apiRoute: '/vendor-api',
        upstreamUrl: 'http://55.55.55.2:8081'
    },
    '9b94676ac49d6497cd764cabcbdb4ac9b467adc9b467d64d64d4dbc9764d76d4' :
    {
        apiRoute: '/app-api',
        upstreamUrl: 'http://55.55.55.2:8080'
    },
    'ccb6ea44ceeacb6e54a46c9a96c54a4c5eac6e6e6d66d6e655798798e7987eeed' :
    {
        apiRoute: '/content',
        upstreamUrl: 'http://55.55.55.2:800'
    }
};


// var route = {
//     apiRoute: containerInfo.Labels.api_route,
//     upstreamUrl: getUpstreamUrl(containerDetails)
// };

// routes[containerInfo.Id] = route;


// create and start http server
var server = http.createServer(function (req, res)
{
    for (id in routes) {
        if (routes.hasOwnProperty(id) && handleRoute(routes[id], req, res)) {
            return;
        }
    }

    returnError(req, res);
});

console.log('API gateway is listening on port: %d', httpPort);
server.listen(httpPort);

// create proxy
var proxy = httpProxy.createProxyServer();
proxy.on('error', function (err, req, res) {
    console.log(err);
    returnError(req, res);
});

// proxy HTTP request / response to / from destination upstream service if route matches
function handleRoute(route, req, res) {
    var url = req.url;
    var parsedUrl = parseurl(req);

    if (parsedUrl.path.indexOf(route.apiRoute) === 0) {
        req.url = url.replace(route.apiRoute, '');
        proxy.web(req, res, { target: route.upstreamUrl });
        return true;
    }
}



// send 502 response to the client in case of an error
function returnError(req, res) {
    console.log(req.url);
    res.writeHead(502, {'Content-Type': 'text/plain'});
    res.write('Bad Gateway for: ' + req.url);
    res.end();
}
