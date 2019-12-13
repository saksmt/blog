{ pkgs ? import <nixpkgs> {} }:
let
  stdenv = pkgs.stdenv;
in rec {
  myProject = stdenv.mkDerivation {
    name = "blog";
    buildInputs = [ pkgs.nodejs pkgs.automake pkgs.sbt pkgs.docker ];
    shellHook = [''
    alias build="sbt build"
    alias build-prod="rm -rf frontend/target/prod-web; sbt buildProd && docker build -t blog-frontend frontend"
    alias build-and-push-prod="\
        rm -rf frontend/target/prod-web; \
        sbt buildProd && docker build -t blog-frontend frontend && \
        docker tag blog-frontend docker.smt.run/blog-frontend:latest && \
        docker push docker.smt.run/blog-frontend:latest"
    echo "Use build to build and build-watch to watch changes and rebuild"
    ''];
  };
}