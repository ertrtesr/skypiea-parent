Package.describe({
  name: "danialf:ng-file-file",
  "version": "12.2.13",
  summary: "Lightweight Angular directive to file files with optional FileAPI shim for cross browser support",
  git: "https://github.com/danialfarid/ng-file-file.git"
});

Package.onUse(function (api) {
  api.use('angular:angular@1.2.0', 'client');
  api.addFiles('ng-file-file-all.js', 'client');
});

