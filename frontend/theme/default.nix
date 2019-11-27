{ pkgs ? import <nixpkgs> {} }:
let
  stdenv = pkgs.stdenv;
in rec {
  myProject = stdenv.mkDerivation {
    name = "blog-frontend";
    buildInputs = [ pkgs.nodejs pkgs.automake ];
    shellHook = [''
    alias build="npm run build"
    alias build-watch="npm run build-watch"
    echo "Use build to build and build-watch to watch changes and rebuild"
    ''];
  };
}