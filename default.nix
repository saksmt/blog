{ pkgs ? import <nixpkgs> {} }:
let
  stdenv = pkgs.stdenv;
in rec {
  myProject = stdenv.mkDerivation {
    name = "blog-frontend";
    buildInputs = [ pkgs.nodejs pkgs.automake pkgs.sbt ];
    shellHook = [''
    alias build="sbt build"
    alias build-watch="npm run build-watch"
    echo "Use build to build and build-watch to watch changes and rebuild"
    ''];
  };
}