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

    function build-and-push-prod() {
      rm -rf frontend/target/prod-web
      sbt buildProd && docker build -t blog-frontend frontend && \
      docker tag blog-frontend cloud.canister.io:5000/saksmt/blog-frontend:''${1:-unstable} && \
      docker push cloud.canister.io:5000/saksmt/blog-frontend:''${1:-unstable}
    }
    echo "Command reference:"
    echo " - build - build app"
    echo " - build-prod - prod build"
    echo " - build-and-push-prod [VERSION] - build prod and push docker image with specified VERSION or unstable if not present"
    ''];
  };
}