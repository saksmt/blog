# Sources of [saksmt.dev](saksmt.dev)

## Build

### With nix
```bash
$ nix-shell
[shell] $ build-prod
```

### Manually

#### Prerequisites
 - npm
 - sbt
 - automake (for dependencies)

#### Commands

```bash
$ sbt buildProd
$ docker build -t blog-frontend frontend
```

## Directory layout

```
 - /backend - backend scala sources
 - /shared - scala sources shared between frontend and backend
 - /frontend - frontend sources
    - /src/main/scala - scalajs sources
    - /src/main/js - js sources
    - /src/main/scss - styles
    - /src/main/html - base template | init page
    - /src/main/nginx - frontend static handler nginx config
    - /Dockerfile - docker image for frontend with static handler 
```

## Deployment

See [saksmt.dev-server-cluster](https://github.com/saksmt/saksmt.dev-server-cluster)
