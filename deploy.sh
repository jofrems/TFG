#!/bin/bash

fail() {
	echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
	echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
	echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
	echo "!!!!"
	echo "!!!!   DEPLOY FAILED "
	echo "!!!!"
	echo "!!!!   b:$*    !!!!!!!!! "
	echo "!!!!"
	exit 1
}

# Verify tests
./mvnw test  || fail "./mvnw test"
CI=1 yarn test -i --testTimeout 20000 || fail "yarn test"

# Compile
yarn build  || fail "yarn build"
./mvnw compile  || fail "./mvnw compile"
./mvnw dependency:copy-dependencies  || fail "./mvnw dependency:copy-dependencies"

# Prepare deploy folder
rm -fr ./deploy
mkdir ./deploy
mv ./build ./deploy
mv ./target ./deploy

# Commit and deploy
git add --all || fail "git add --all"
git commit -m "DEPLOY v$(cat package.json | grep version | cut -d '"' -f 4) $(date "+DATE: %Y-%m-%d / TIME: %H:%M:%S")" || fail "git commit"
git push --force heroku || fail "git push heroku master"

# All ok.
heroku open
echo "OK."
